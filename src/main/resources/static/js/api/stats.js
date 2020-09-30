import Vue from 'vue'

const stats = Vue.resource('/stats{/id}')

export default {
    add: dailyStats => stats.save({}, dailyStats),
    update: dailyStats => stats.update({id: dailyStats.id}, dailyStats),
    remove: id => stats.remove({id}),
}