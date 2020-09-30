import Vue from 'vue'
// import VueResource from 'vue-resource'
import '@babel/polyfill'
import 'api/resource'
import moment from 'moment'
import VueMoment from 'vue-moment'
import Vuelidate from 'vuelidate'
import App from 'pages/App.vue'
import store from "./store/store.js"
import 'mystyles.css'
// import moment from 'moment'
// import VueMoment from 'vue-moment'
// import Vuelidate from 'vuelidate'

Vue.use(VueMoment, { moment })
Vue.use(Vuelidate)

new Vue({
    el: '#app',
    store,
    render: a => a(App)
})
