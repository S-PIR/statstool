<template>
    <div style="position: relative; width: 400px;">
        <daily-stats-form :stats="stats" :statsAttr="dailyStats" />
        <daily-stats-row v-for="dailyStats in stats"
                     :key="dailyStats.id"
                     :dailyStats="dailyStats"
                     :editDailyStats="editDailyStats"
                     :deleteDailyStats="deleteDailyStats"
                     :stats="stats" />
    </div>
</template>

<script>
    import DailyStatsRow from 'components/stats/DailyStatsRow.vue'
    import DailyStatsForm from 'components/stats/DailyStatsForm.vue'

    export default {
        props: ['stats'],
        components: {
            DailyStatsRow,
            DailyStatsForm
        },
        data() {
            return {
                dailyStats: null
            }
        },
        methods: {
            editDailyStats(dailyStats) {
                this.dailyStats = dailyStats
            },
            deleteDailyStats(dailyStats) {
                this.$resource('/stats{/id}').remove({id: dailyStats.id}).then(result => {
                    if (result.ok) {
                        this.stats.splice(this.stats.indexOf(dailyStats), 1)
                    }
                })
            }
        }
    }
</script>

<style>
</style>