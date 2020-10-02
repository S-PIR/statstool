<template>
    <transition name="modal-fade">
        <div class="modal-backdrop" ref="modal" tabindex="0" @keyup.esc="$emit('close')">
            <div class="modal" role="dialog" aria-labelledby="modalTitle" aria-describedby="modalDescription" >
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

                        <div class="" :class="{ 'form__error': $v.finInstrument.$error }">
                            <select class="form__input" v-model.trim.lazy="$v.finInstrument.$model">
                                <option value="" disabled selected hidden>Please Choose...</option>
                                <option v-for="option in instruments" :value="option">{{option}}</option>
                            </select>
                            <div class="form__error" v-if="!$v.finInstrument.required">Field is required</div>
                            <div class="form__error" v-if="!$v.finInstrument.minLength">
                                Instrument must have at least {{$v.finInstrument.$params.minLength.min}} letters.
                            </div>
                        </div>

                        <div class="" :class="{ 'form__error': $v.price.$error }">
                            <input class="form__input" v-model.number.trim.lazy="$v.price.$model" placeholder="price"/>
                            <div class="form__error" v-if="!$v.price.required">Field is required</div>
                            <div class="form__error" v-if="!$v.price.between">
                                Must be between {{$v.price.$params.between.min}} and {{$v.price.$params.between.max}}
                            </div><br>
                        </div>

                        <input class="btn" type="button" value="Save" @click="save"/>
                        <p class="form__error" v-if="submitStatus === 'ERROR'">Please fill the form correctly.</p>
                    </slot>
                </section>
            </div>
        </div>
    </transition>
</template>

<script>
    import { between, minLength, required } from "vuelidate/lib/validators"
    import { mapState, mapActions } from "vuex"

    export default {
        data() {
            return {
                id: null,
                statusDate: null,
                finInstrument: null,
                price: null,
                showModal: false,
                submitStatus: null,
            }
        },
        computed: mapState(['instruments']),

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
                between: between(0, 11000)
            },
        },
        methods: {
            ...mapActions(['addDailyStatsAction']),
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
                    this.addDailyStatsAction(dailyStats)
                    this.id = null
                    this.statusDate = null
                    this.finInstrument = null
                    this.price = null
                    this.$emit('close')
                }
            },
        },
        mounted() {
            this.$refs.modal.focus()
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