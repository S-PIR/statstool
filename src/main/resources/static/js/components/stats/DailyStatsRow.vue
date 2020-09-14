<template>
    <div>
        <i>({{ dailyStats.id }})</i>
        <span style="position: absolute; right: 65%">
            {{ dailyStats.statusDate }}
        </span>
        <span style="position: absolute; right: 40%">
            {{ dailyStats.finInstrument.instrumentName }}
        </span>
        <span style="position: absolute; left: 63%">
            {{ dailyStats.price }}
        </span>

        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="Delete" @click="del" />
        </span>
    </div>
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

<style>
</style>