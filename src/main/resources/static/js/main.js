import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue';
import 'mystyles.css';
import moment from 'moment';
import VueMoment from 'vue-moment';
import Vuelidate from 'vuelidate'
import TrendChart from "vue-trend-chart"

Vue.use(VueResource)
Vue.use(VueMoment, { moment })
Vue.use(Vuelidate)
Vue.use(TrendChart)

new Vue({
    el: '#app',
    render: a => a(App)
})
