<template>
    <div>
        <daily-stats-list :stats="stats" />
    </div>
</template>

<script>
    import DailyStatsList from 'components/stats/DailyStatsList.vue'
    export default {
        components: {
            DailyStatsList
        },
        data() {
            return {
                stats: frontendData.stats
            }
        }
    }
</script>

<style>
</style>