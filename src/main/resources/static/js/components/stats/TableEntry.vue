<template>
    <tr>
        <td>
            <label
                    class=""
                    :class="{ editing: getEditedField === statsColumns[0]}"
                    @dblclick="beginEdit(statsColumns[0])"
            >
                {{ this.$moment().format(dailyStats.statusDate, 'DD-MM-YYYY') }}
            </label>
            <div class="" :class="{ 'form__error': $v.statusDate.$error }">
                <input
                        class=""
                        :class="{ editing: !(getEditedField === statsColumns[0]) }"
                        type="date"
                        v-model.trim.lazy="$v.statusDate.$model"
                        @keyup.enter="edit()"
                        v-field-focus = "getEditedField === statsColumns[0]"
                        @blur="cancelEdit()"
                        @keyup.esc="cancelEdit()"
                />
                <div class="form__error" v-if="!$v.statusDate.required">Field is required</div>

            </div>
        </td>

        <td>
            <label class=""
                   :class="{ editing: getEditedField === statsColumns[1] }"
                   @dblclick="beginEdit(statsColumns[1])"
            >
                {{ dailyStats.finInstrument.instrumentName }}
            </label>
            <div class="" :class="{ 'form__error': $v.finInstrument.$error }">
                <input
                        class=""
                        :class="{ editing: !(getEditedField === statsColumns[1]) }"
                        type="text"
                        v-model.trim.lazy="$v.finInstrument.$model"
                        v-field-focus = "getEditedField === statsColumns[1]"
                        @keyup.enter="edit()"
                        @blur="cancelEdit()"
                        @keyup.esc="cancelEdit()"

                />
                <div class="form__error" v-if="!$v.finInstrument.required">Field is required</div>
                <div class="form__error" v-if="!$v.finInstrument.minLength">
                    Instrument must have at least {{$v.finInstrument.$params.minLength.min}} letters.
                </div>
            </div>
        </td>

        <td>
            <label class=""
                   :class="{ editing: getEditedField === statsColumns[2] }"
                   @dblclick="beginEdit(statsColumns[2])"
            >
                {{ dailyStats.price }}
            </label>
            <div class="" :class="{ 'form__error': $v.price.$error }">
                <input
                        class=""
                        :class="{ editing: !(getEditedField === statsColumns[2]) }"
                        type="text"
                        v-model.number.trim.lazy="$v.price.$model"
                        v-field-focus = "getEditedField === statsColumns[2]"
                        @keyup.enter="edit()"
                        @blur="cancelEdit()"
                        @keyup.esc="cancelEdit()"
                />
                <div class="form__error" v-if="!$v.price.required">Field is required</div>
                <div class="form__error" v-if="!$v.price.between">
                    Must be between {{$v.price.$params.between.min}} and {{$v.price.$params.between.max}}
                </div>
            </div>
        </td>
        <td>
            <input type="button" value="Delete" @click="del" />
        </td>

    </tr>
</template>

<script>

    import {between, minLength, required} from "vuelidate/lib/validators";

    function getIndex(list, id) {
        for (let i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }

    export default {
        props: ['dailyStats', 'editDailyStats', 'deleteDailyStats', 'stats', 'statsColumns'],
        data() {
            return {
                id: this.dailyStats.id,
                statusDate: this.dailyStats.statusDate,
                finInstrument: this.dailyStats.finInstrument.instrumentName,
                price: this.dailyStats.price,
                editedDailyStats: '',
                editedField: '',

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
                between: between(1, 11000)
            },
        },
        computed: {
            getEditedField() {
                if (this.editedDailyStats === this.dailyStats) {
                    return this.editedField
                }
            }
        },
        methods: {
            edit() {
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
                    this.$resource('/stats{/id}').update({id: this.id}, dailyStats).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.stats, data.id)
                            this.stats.splice(index, 1, data)
                            this.id = data.id
                            this.statusDate = data.statusDate
                            this.finInstrument = data.finInstrument.instrumentName
                            this.price = data.price
                            this.editedDailyStats = ''
                            this.editedField = ''
                        })
                    )
                }
            },

            del() {
                this.deleteDailyStats(this.dailyStats)
            },

            beginEdit(editedField) {
                this.editedField = editedField
                this.editedDailyStats = this.dailyStats;
            },

            cancelEdit() {
                this.id = this.dailyStats.id
                this.statusDate = this.dailyStats.statusDate
                this.finInstrument = this.dailyStats.finInstrument.instrumentName
                this.price = this.dailyStats.price
                this.editedDailyStats = ''
                this.editedField = ''
            },


        },

        directives: {
            "field-focus": function(el, binding) {
                if (binding.value) {
                    el.focus();
                }
            }
        }

    }

</script>

<style scoped>
    .edit {
        position: relative;
        margin: 0;
        width: 100%;
        font-size: 24px;
        font-family: inherit;
        font-weight: inherit;
        line-height: 1.4em;
        color: inherit;
        padding: 6px;
        border: 1px solid #999;
        box-shadow: inset 0 -1px 5px 0 rgba(0, 0, 0, 0.2);
        box-sizing: border-box;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

    .editing {
        display: none;
    }
</style>