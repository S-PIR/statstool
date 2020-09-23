<template>
    <div class="small">
        <select v-model="index" @change="getChartData()">
            <option value="" disabled selected hidden>{{ instruments[1] }}</option>
            <option v-for="(option, index) in instruments" :value="index">{{ option }}</option>
        </select>
        <line-chart :chart-data="datacollection"></line-chart>
    </div>
</template>

<script>
    import LineChart from 'components/stats/LineChart.vue'

    export default {
        components: {
            LineChart
        },
        props: ['stats', 'instruments'],
        data () {

            return {
                instrument: this.instruments[0],
                datacollection: this.fillData(),
                index: 0,
                dateChart: null,
            }
        },
        mounted () {
            this.fillData()
        },
        methods: {

            fillData () {

                this.$resource('/instrument/stats{/id}').get({id: this.index}).then(result =>
                    result.json().then(data => {
                        this.data = data;
                        // alert(data.dataSets[0].label)
                        // alert(data.dataSets[0].backgroundColor)
                        // alert(data.dataSets[0].data[5])
                    })
                )
                this.datacollection = {
                    labels: [40, 50, 60,70, 80, 180],
                    datasets: [
                        {
                            label: 'Data One',
                            borderColor: 'red',
                            backgroundColor: 'transparent',
                            data: [30, 40, 28, 23, 23, 17, 26, 45, 19]
                        },
                    ]
                }
            },
            async getChartData() {
//                 let schedule = `{
// "labels": [
// "2019-01-01",
// "2019-01-04",
// "2019-01-05",
// "2019-01-08",
// "2019-10-07",
// "2019-10-08",
// "2019-10-09",
// "2019-10-11"
// ],
//     "dataSets": [
//         {
//             "label": "Газпром",
//             "backgroundColor": "#FF0000",
//             "data": [
//                 2500.0,
//                 2550.0,
//                 2600.0,
//                 2650.0,
//                 2100.0,
//                 2150.0,
//                 2200.0,
//                 2100.0
//             ]
//         }
//     ]
// }`;
                alert(this.index)
                let f
                const response = await fetch(`/instrument/stats/${1}`)
                    // this.$resource('/instrument/stats/1').get({id: this.index})
                    // .then(result =>
                    // result.json().then(data => {
                //       f = data
                // alert(data.labels[0])
                // alert(data.dataSets[0].label)
                // alert(data.dataSets[0].backgroundColor)
                // alert(data.dataSets[0].data[5])
                //     })
                // )
                console.log(response)
            }
        }
    }
</script>

<style>
    .small {
        max-width: 600px;
        margin:  150px auto;
    }
</style>
