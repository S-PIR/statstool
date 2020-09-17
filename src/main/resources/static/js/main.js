import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue';
import 'mystyles.css';
import moment from 'moment';
import VueMoment from 'vue-moment';


Vue.use(VueResource)
Vue.use(VueMoment, { moment })


new Vue({
    el: '#app',
    render: a => a(App)
})