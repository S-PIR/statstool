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
    import { mapState } from "vuex"
    import instrumentApi from "api/instrument"

    export default {
        components: {
            LineChart
        },
        computed: mapState(['instruments', 'stats']),
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
            async fillData() {
                this.loaded = false
                const result = await instrumentApi.get(this.index)
                this.datacollection = await result.json()
                this.loaded = true
            }
        }
    };




</script>

<style>

</style>
