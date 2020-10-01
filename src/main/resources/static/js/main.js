import Vue from 'vue'
import '@babel/polyfill'
import 'api/resource'
import router from "router/router";
import moment from 'moment'
import VueMoment from 'vue-moment'
import Vuelidate from 'vuelidate'
import App from 'pages/App.vue'
import store from "store/store"
import 'mystyles.css'
import { connect } from './util/ws'

connect()

Vue.use(VueMoment, { moment })
Vue.use(Vuelidate)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})
