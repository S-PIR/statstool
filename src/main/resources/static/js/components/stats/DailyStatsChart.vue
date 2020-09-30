<template>
    <div class="small">
        <select v-model="index" @change="fillData()">
            <option value="" disabled selected hidden>{{ instruments[0] }}</option>
            <option v-for="(option, index) in instruments" :value="index">{{ option }}</option>
        </select>
        <line-chart v-if="loaded" :chart-data="datacollection"></line-chart>
    </div>
</template>

<script>
    import LineChart from 'components/stats/LineChart.vue'
    import { mapState } from "vuex";

    export default {
        components: {
            LineChart
        },
        computed: mapState(['stats', 'instruments']),
        data() {
            return {
                datacollection: this.fillData(),
                index: 0,
                dateChart: null,
                loaded: false,
            }
        },
        mounted() {
            this.fillData()
        },
        watch: {
            stats(newVal, oldVal) {
                this.fillData()
            }
        },
        methods: {
            fillData() {
                this.loaded = false
                this.$resource('/instrument/stats{/id}').get({id: this.index})
                    .then(result =>
                        result.json().then(data => {
                            this.datacollection = data
                            this.loaded = true
                        })
                    )
            }
        }
    };




</script>

<style>

</style>
