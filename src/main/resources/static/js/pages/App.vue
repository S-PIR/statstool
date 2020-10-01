<template>
    <div class="app">
        <h2>StatsTool</h2>
        <router-view></router-view>
    </div>
</template>

<script>
    import {addHandler} from "util/ws"
    import { mapMutations } from "vuex"

    export default {
        methods: {
            ...mapMutations(['addDailyStatsMutation', 'updateDailyStatsMutation', 'removeDailyStatsMutation']),
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'DAILY_STATS'){
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addDailyStatsMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateDailyStatsMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeDailyStatsMutation(data.body)
                            break
                        default:
                            console.log(`Looks like the event type is unknown "${data.eventType}"`)
                    }
                } else {
                    console.log(`Looks like the event type is unknown "${data.objectType}"`)
                }
            })
        },
    }
</script>

<style>
    .app {
        text-align: center;
    }
</style>