<template>
    <div>
        <table>
            <thead>
            <tr>
                <th v-for="column in statsColumns"
                    @click="sortBy(column)"
                    :class="{ active: sortKey === column }">
                    {{ column | capitalize }}
                    <span class="arrow" :class="sortOrders[column] > 0 ? 'asc' : 'dsc'"/>
                </th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <table-entry v-for="dailyStats in filteredStats"
                         :key="dailyStats.id"
                         :dailyStats="dailyStats"
                         :editDailyStats="editDailyStats"
                         :deleteDailyStats="deleteDailyStats"
                         :stats="stats"/>
            </tbody>
        </table>
    </div>


</template>

<script>
    import TableEntry from 'components/stats/TableEntry.vue'

    export default {
        components: {
            TableEntry
        },
        props: [
            'editDailyStats',
            'deleteDailyStats',
            'stats',
            'statsColumns',

        ],
        data() {
            let sortOrders = {};
            this.statsColumns.forEach(function(column) {
                sortOrders[column] = 1;
            });
            return {
                sortKey: "",
                sortOrders: sortOrders,
                dailyStats: null,
            };
        },
        computed: {
            filteredStats() {
                let sortKey = this.sortKey;
                let order = this.sortOrders[sortKey] || 1
                let stats = this.stats
                let x, y
                const ColumnInstrument = this.statsColumns[1]
                if (sortKey) {
                    stats = stats.slice().sort(function(a, b) {

                        if (sortKey === ColumnInstrument){
                            x = a[sortKey].instrumentName
                            y = b[sortKey].instrumentName
                            return x.localeCompare(y) * order;
                        } else {
                            x = a[sortKey]
                            y = b[sortKey]
                            return (x == y ? 0 : x > y ? 1 : -1) * order;
                        }
                    });
                }
                return stats;
            }
        },
        filters: {
            capitalize(str) {
                return str.charAt(0).toUpperCase() + str.slice(1);
            }
        },
        methods: {
            sortBy(column) {
                this.sortKey = column;
                this.sortOrders[column] = this.sortOrders[column] * -1;
            },

        }
    }

</script>

<style>
</style>