<template>
    <div class="app">
        <h3>StatsTool</h3>
        <daily-stats :stats="stats" :instruments="instruments" :statsColumns="statsColumns"/>
    </div>
</template>

<script>
    import DailyStats from 'components/stats/DailyStats.vue'
    export default {
        components: {
            DailyStats
        },
        data() {
            return {
                stats: frontendData.stats,
                instruments: frontendData.instruments,
                statsColumns: [ "statusDate", "finInstrument", "price" ],
            }
        }
    }
</script>

<style>
    .app {
        text-align: center;
    }
</style>