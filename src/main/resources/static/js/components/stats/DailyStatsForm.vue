<template>
    <div>
        <input type="date" v-model="statusDate" />
        <input type="text" v-model="finInstrument" placeholder="Financial instrument" />
        <input type="number" v-model="price" placeholder="Price" />
        <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (let i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }

    export default {
        props: ['stats', 'statsAttr'],
        data() {
            return {
                id: '',
                statusDate: '',
                finInstrument: '',
                price: ''
            }
        },
        watch: {
            statsAttr(newVal, oldVal) {
                this.id = newVal.id
                this.statusDate = newVal.statusDate
                this.finInstrument = newVal.finInstrument.instrumentName
                this.price = newVal.price
            }
        },
        methods: {
            save() {
                const dailyStats = { statusDate: this.statusDate, finInstrument: this.finInstrument, price: this.price,  }
                if (this.id) {
                    this.$resource('/stats{/id}').update({id: this.id}, dailyStats).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.stats, data.id)
                            this.stats.splice(index, 1, data)
                            this.id = ''
                            this.statusDate = ''
                            this.finInstrument = ''
                            this.price = ''
                        })
                    )
                } else {
                    this.$resource('/stats{/id}').save({}, dailyStats).then(result =>
                        result.json().then(data => {
                            this.stats.push(data)
                            this.statusDate = ''
                            this.finInstrument = ''
                            this.price = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>
</style>
