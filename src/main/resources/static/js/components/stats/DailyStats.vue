<template>
    <div>
        <div id="modal-create">
            <button type="button" class="btn" @click="showModal">Create new entry</button>
            <modal-create-entry
                    :stats="stats"
                    :instruments="instruments"
                    v-show="isModalVisible"
                    @close="closeModal"
            />
        </div><br>

        <daily-stats-table
                         :editDailyStats="editDailyStats"
                         :deleteDailyStats="deleteDailyStats"
                         :stats="stats"
                         :statsColumns="statsColumns"
                         :instruments="instruments"
        />
        <daily-stats-chart :stats="stats" :instruments="instruments"/>

    </div>
</template>

<script>
    import DailyStatsTable from 'components/stats/DailyStatsTable.vue'
    import ModalCreateEntry from "components/stats/ModalCreateEntry.vue"
    import DailyStatsChart from "components/stats/DailyStatsChart.vue"

    export default {
        props: ['stats', 'statsColumns', 'instruments'],
        components: {
            DailyStatsTable,
            ModalCreateEntry,
            DailyStatsChart,

        },
        data() {
            return {
                dailyStats: null,
                isModalVisible: false,
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
            },
            showModal() {
                this.isModalVisible = true;
            },
            closeModal() {
                this.isModalVisible = false;
            }
        }
    }
</script>

<style>

</style>