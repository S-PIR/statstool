<template>
    <transition name="modal-fade">
        <div class="modal-backdrop">
            <div class="modal" role="dialog" aria-labelledby="modalTitle" aria-describedby="modalDescription">
                <header class="modal-header" id="modalTitle">
                    <slot name="header">
                        <button type="button" class="btn-close" @click="close" aria-label="Close modal">Close</button>
                    </slot>
                </header>
                <section class="edit_form" id="modalDescription">
                    <slot name="body">
                        <div >Fill the form to create new entry.</div><br>
                        <div class="" :class="{ 'form__error': $v.statusDate.$error }">
                            <input class="form__input" type="date" v-model.trim.lazy="$v.statusDate.$model" />
                            <div class="form__error" v-if="!$v.statusDate.required">Field is required</div>
                        </div>
                        <tree-view :data="$v.statusDate" :options="{rootObjectKey: '$v.statusDate', maxDepth: 2}"/>

                        <div class="" :class="{ 'form__error': $v.finInstrument.$error }">
                            <input class="form__input" v-model.trim.lazy="$v.finInstrument.$model" placeholder="instrument"/>
                            <div class="form__error" v-if="!$v.finInstrument.required">Field is required</div>
                            <div class="form__error" v-if="!$v.finInstrument.minLength">
                                Instrument must have at least {{$v.finInstrument.$params.minLength.min}} letters.
                            </div>
                        </div>
                        <tree-view :data="$v.finInstrument" :options="{rootObjectKey: '$v.finInstrument', maxDepth: 2}"/>

                        <div class="" :class="{ 'form__error': $v.price.$error }">
                            <input class="form__input" v-model.trim.lazy="$v.price.$model" placeholder="price"/>
                            <div class="form__error" v-if="!$v.price.required">Field is required</div>
                            <div class="form__error" v-if="!$v.price.between">
                                Must be between {{$v.price.$params.between.min}} and {{$v.price.$params.between.max}}
                            </div><br>
                        </div>
                        <tree-view :data="$v.price" :options="{rootObjectKey: '$v.price', maxDepth: 2}"></tree-view>

                        <input class="btn" type="button" value="Save" @click="save" />
                        <p class="form__error" v-if="submitStatus === 'ERROR'">Please fill the form correctly.</p>
                    </slot>
                </section>
            </div>
        </div>
    </transition>
</template>

<script>
    import {between, minLength, required} from "vuelidate/lib/validators";
    export default {
        props: ['stats'],
        data() {
            return {
                id: '',
                statusDate: '',
                finInstrument: '',
                price: '',
                showModal: false,
                submitStatus: null,
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
            close() {
                this.$emit('close');
            },
            save() {
                this.$v.$touch()
                if (this.$v.$invalid) {
                    this.submitStatus = 'ERROR'
                } else {
                    const dailyStats = {
                        id: this.id,
                        statusDate: this.statusDate,
                        finInstrument: this.finInstrument,
                        price: this.price,
                    }
                    this.$resource('/stats{/id}').save({}, dailyStats).then(result =>
                        result.json().then(data => {
                            this.stats.push(data)
                            this.id = ''
                            this.statusDate = ''
                            this.finInstrument = ''
                            this.price = ''
                        })
                    )
                    this.$emit('close')
                }
            }
        }
    }
</script>

<style>
</style>