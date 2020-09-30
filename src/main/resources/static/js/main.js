import Vue from 'vue'
import '@babel/polyfill'
import 'api/resource'
import router from "router/router.js";
import moment from 'moment'
import VueMoment from 'vue-moment'
import Vuelidate from 'vuelidate'
import App from 'pages/App.vue'
import store from "store/store.js"
import 'mystyles.css'


Vue.use(VueMoment, { moment })
Vue.use(Vuelidate)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})
