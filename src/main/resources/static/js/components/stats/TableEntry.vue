<template>
    <tr>
        <td>
            {{ this.$moment().format(dailyStats.statusDate, 'DD-MM-YYYY') }}
        </td>
        <td>
            {{ dailyStats.finInstrument.instrumentName }}
        </td>
        <td>
            {{ dailyStats.price }}
        </td>
        <td>
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="Delete" @click="del" />
        </td>

    </tr>
</template>

<script>
    export default {
        props: ['dailyStats', 'editDailyStats', 'deleteDailyStats', 'stats'],
        methods: {
            edit() {

                this.editDailyStats(this.dailyStats)
            },
            del() {
                this.deleteDailyStats(this.dailyStats)
            }
        }
    }

</script>

<style scoped>

</style>