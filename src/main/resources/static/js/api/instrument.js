import Vue from 'vue'

const stats = Vue.resource('/instrument/stats{/id}')

export default {
    get: id => stats.get({id}),
}