<template>
    <div>
        <daily-stats-table :stats="stats" :statsColumns="statsColumns"/>
    </div>
</template>

<script>
    import DailyStatsTable from 'components/stats/DailyStatsTable.vue'
    export default {
        components: {
            DailyStatsTable
        },
        data() {
            return {
                stats: frontendData.stats,
                statsColumns: ["statusDate", "finInstrument", "price"]
            }
        }
    }
</script>

<style>
</style>