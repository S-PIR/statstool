import Vue from 'vue'
import VueRouter from 'vue-router'
import DailyStats from "components/stats/DailyStats.vue";


Vue.use(VueRouter)

const routes = [
    { path: '/', component: DailyStats },
    { path: '*', component: DailyStats },

]

export default new VueRouter({
    mode: 'history',
    routes
})