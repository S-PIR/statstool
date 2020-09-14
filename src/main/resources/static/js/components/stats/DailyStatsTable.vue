<template>
    <div>
        <daily-stats-form :stats="stats" :statsAttr="dailyStats" /><br>
        <daily-stats-entry
                         :editDailyStats="editDailyStats"
                         :deleteDailyStats="deleteDailyStats"
                         :stats="stats"
                         :statsColumns="statsColumns"
                         :dailyStats="dailyStats"

        /><br>
            <div id="modal-create">
                <button type="button" class="btn" @click="showModal">Create new</button>
                <modal-create-entry
                        :stats="stats"
                        v-show="isModalVisible"
                        @close="closeModal"
                />
            </div>
    </div>
</template>

<script>
    import DailyStatsForm from 'components/stats/DailyStatsForm.vue'
    import DailyStatsEntry from 'components/stats/DailyStatsEntry.vue'
    import ModalCreateEntry from "components/stats/ModalCreateEntry.vue";

    export default {
        props: ['stats', 'statsColumns'],
        components: {
            DailyStatsForm,
            DailyStatsEntry,
            ModalCreateEntry,
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