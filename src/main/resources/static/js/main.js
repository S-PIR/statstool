import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue';
import 'mystyles.css';
import moment from 'moment';
import VueMoment from 'vue-moment';
import Vuelidate from 'vuelidate'


Vue.use(VueMoment, { moment })
Vue.use(VueResource)
Vue.use(Vuelidate)


new Vue({
    el: '#app',
    render: a => a(App)
})
