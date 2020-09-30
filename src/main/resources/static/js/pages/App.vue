<template>
    <div class="app">
        <h2>StatsTool</h2>
        <daily-stats/>
    </div>
</template>

<script>
    import DailyStats from 'components/stats/DailyStats.vue'
    export default {
        components: {
            DailyStats
        },
    }
</script>

<style>
    .app {
        text-align: center;
    }
</style>