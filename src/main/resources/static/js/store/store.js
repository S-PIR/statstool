import Vue from 'vue'
import Vuex from 'vuex'
import statsApi from "api/stats"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        stats: frontendData.stats,
        instruments: frontendData.instruments,
        statsColumns: [ "statusDate", "finInstrument", "price" ],
    },
    mutations: {
        addDailyStatsMutation(state, dailyStats){
            state.stats = [
                ...state.stats,
                dailyStats
            ]
        },
        updateDailyStatsMutation(state, dailyStats){
            const updateIndex = state.stats.findIndex(item => item.id === dailyStats.id)

            state.stats = [
                ...state.stats.slice(0, updateIndex),
                dailyStats,
                ...state.stats.slice(updateIndex + 1)
            ]
        },
        removeDailyStatsMutation(state, dailyStats){
            const deletionIndex = state.stats.findIndex(item => item.id === dailyStats.id)

            if (deletionIndex > -1) {
                state.stats = [
                    ...state.stats.slice(0, deletionIndex),
                    ...state.stats.slice(deletionIndex + 1)
                ]
            }
        },
    },
    actions: {
        async addDailyStatsAction({commit, state}, dailyStats){
            const result = await statsApi.add(dailyStats)
            const data = await result.json()
            commit('addDailyStatsMutation', data)
        },
        async updateDailyStatsAction({commit}, dailyStats){
            const result = await statsApi.update(dailyStats)
            const data = await result.json()
            commit('updateDailyStatsMutation', data)
        },
        async removeDailyStatsAction({commit}, dailyStats){
            const result = await statsApi.remove(dailyStats.id)

            if (result.ok) {
                commit('removeDailyStatsMutation', dailyStats)
            }
        },

    }
})