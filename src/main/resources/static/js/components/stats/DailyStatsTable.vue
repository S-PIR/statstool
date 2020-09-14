<template>
    <div>
        <daily-stats-form :stats="stats" :statsAttr="dailyStats" /><br>
        <daily-stats-entry
                         :editDailyStats="editDailyStats"
                         :deleteDailyStats="deleteDailyStats"
                         :stats="stats"
                         :statsColumns="statsColumns"
                         :dailyStats="dailyStats"

        />
    </div>
</template>

<script>
    import DailyStatsForm from 'components/stats/DailyStatsForm.vue'
    import DailyStatsEntry from 'components/stats/DailyStatsEntry.vue'

    export default {
        props: ['stats', 'statsColumns'],
        components: {
            DailyStatsEntry,
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