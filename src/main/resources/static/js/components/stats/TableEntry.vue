<template>
    <tr>
        <td>
            <label
                    class=""
                    :class="{ editing: getEditedField === statsColumns[0]}"
                    @dblclick="beginEdit(statsColumns[0])"
            >
                {{ this.$moment(dailyStats.statusDate).format('DD.MM.YYYY') }}
            </label>
            <div class="" :class="{ 'form__error': $v.statusDate.$error }">
                <input
                        class=""
                        :class="{ editing: !(getEditedField === statsColumns[0]) }"
                        type="date"
                        v-model.trim="$v.statusDate.$model"
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
                {{ dailyStats.finInstrument.instrument }}
            </label>
            <div class="" :class="{ 'form__error': $v.finInstrument.$error }">
                <select class=""
                        :class="{ editing: !(getEditedField === statsColumns[1]) }"
                        type="text"
                        v-model.trim="$v.finInstrument.$model"
                        v-field-focus = "getEditedField === statsColumns[1]"
                        @change="edit()"
                        @blur="cancelEdit()"
                        @keyup.esc="cancelEdit()"
                >
                    <option v-for="option in instruments" :value="option">{{option}}</option>
                </select>
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
                        v-model.number.trim="$v.price.$model"
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

    import {between, minLength, required} from "vuelidate/lib/validators"
    import { mapState, mapActions } from "vuex"

    export default {
        props: [
            'dailyStats',
        ],
        data() {
            return {
                id: this.dailyStats.id,
                statusDate: this.dailyStats.statusDate,
                finInstrument: this.dailyStats.finInstrument.instrument,
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
            },
            ...mapState(['stats', 'instruments', 'statsColumns'])
        },
        methods: {
            ...mapActions(['removeDailyStatsAction', 'updateDailyStatsAction']),
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
                    this.updateDailyStatsAction(dailyStats)
                    this.editedField = ''
                    this.editedDailyStats = ''
                }
            },

            del() {
                this.removeDailyStatsAction(this.dailyStats)
            },

            beginEdit(editedField) {
                this.editedField = editedField
                this.editedDailyStats = this.dailyStats;
                this.fillLocalDailyStats()
            },

            cancelEdit() {
                this.editedDailyStats = ''
                this.editedField = ''
                this.fillLocalDailyStats()
            },

            fillLocalDailyStats() {
                this.id = this.dailyStats.id
                this.statusDate = this.dailyStats.statusDate
                this.finInstrument = this.dailyStats.finInstrument.instrument
                this.price = this.dailyStats.price
            }
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

    body {
        font-family: Helvetica Neue, Arial, sans-serif;
        font-size: 14px;
        color: #444;
    }

    table {
        border: 2px solid #42b983;
        border-radius: 3px;
        background-color: #fff;
        margin-left:auto;
        margin-right:auto;
    }

    th {
        background-color: #42b983;
        color: rgba(255, 255, 255, 0.66);
        cursor: pointer;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    td {
        background-color: #f9f9f9;
    }

    th,
    td {
        min-width: 80px;
        padding: 10px 20px;
    }

    th.active {
        color: #fff;
    }

    th.active .arrow {
        opacity: 1;
    }
</style>
