<template>
    <div class="edit_form">
        <form @submit.prevent="save" >
            <div class="" :class="{ 'form__error': $v.statusDate.$error }">
                <input class="form__input" type="date" v-model.trim.lazy="$v.statusDate.$model" />
                <div class="form__error" v-if="!$v.statusDate.required">Field is required</div>
            </div>
            <tree-view :data="$v.statusDate" :options="{rootObjectKey: '$v.statusDate', maxDepth: 2}"></tree-view>

            <div class="" :class="{ 'form__error': $v.finInstrument.$error }">
                <input class="form__input" v-model.trim.lazy="$v.finInstrument.$model" placeholder="instrument name"/>
                <div class="form__error" v-if="!$v.finInstrument.required">Field is required</div>
                <div class="form__error" v-if="!$v.finInstrument.minLength">Instrument must have at least {{$v.finInstrument.$params.minLength.min}} letters.</div>
            </div>
            <tree-view :data="$v.finInstrument" :options="{rootObjectKey: '$v.finInstrument', maxDepth: 2}"></tree-view>

            <div class="" :class="{ 'form__error': $v.price.$error }">
                <input class="form__input" v-model.trim.lazy="$v.price.$model" placeholder="price"/>
                <div class="form__error" v-if="!$v.price.required">Field is required</div>
                <div class="form__error" v-if="!$v.price.between">Must be between {{$v.price.$params.between.min}} and {{$v.price.$params.between.max}}</div><br>
            </div>
            <tree-view :data="$v.price" :options="{rootObjectKey: '$v.price', maxDepth: 2}"></tree-view>

            <input class="btn" type="button" value="Save" @click="save" />
            <p v-if="submitStatus === 'ERROR'" style="color: red">Please fill the form correctly.</p>
        </form>
    </div>
</template>


<script>
    import { required, minLength, between } from 'vuelidate/lib/validators'

    function getIndex(list, id) {
        for (let i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }

    export default {
        props: ['stats', 'statsAttr'],
        data() {
            return {
                id: '',
                statusDate: '',
                finInstrument: '',
                price: '',
                submitStatus: null
            }
        },
        validations: {
            statusDate: {
                required,
            },
            finInstrument: {
                required,
                minLength: minLength(2)
            },
            price: {
                required,
                between: between(0, 10000)
            },
        },
        watch: {
            statsAttr(newVal, oldVal) {
                this.id = newVal.id
                this.statusDate = newVal.statusDate
                this.finInstrument = newVal.finInstrument.instrumentName
                this.price = newVal.price
            }
        },
        methods: {
            save() {
                this.$v.$touch()
                if (this.$v.$invalid) {
                    this.submitStatus = 'ERROR'
                } else {
                    const dailyStats = { statusDate: this.statusDate, finInstrument: this.finInstrument, price: this.price,  }
                    if (this.id) {
                        this.$resource('/stats{/id}').update({id: this.id}, dailyStats).then(result =>
                            result.json().then(data => {
                                const index = getIndex(this.stats, data.id)
                                this.stats.splice(index, 1, data)
                                this.id = ''
                                this.statusDate = ''
                                this.finInstrument = ''
                                this.price = ''
                            })
                        )
                    } else {
                        this.$resource('/stats{/id}').save({}, dailyStats).then(result =>
                            result.json().then(data => {
                                this.stats.push(data)
                                this.statusDate = ''
                                this.finInstrument = ''
                                this.price = ''
                            })
                        )
                    }
                }

            }
        }
    }
</script>

<style>
    .form__input {
        border: none;
        outline: none;
        border-bottom: 2px solid #eee;
        font-size: 1.0em;
        padding: 5px;
        display: block;
        margin: 10px auto 5px;

    }

    .form__error {
        color: red;
        font-size: 1.0em;
        padding-left: 10px;
        margin: auto;
    }

    .edit_form {
        width: 230px;
        padding: 10px;
        display: block;
        margin: auto;
    }



</style>
