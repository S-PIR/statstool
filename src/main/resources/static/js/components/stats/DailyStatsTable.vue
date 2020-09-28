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
                         :stats="stats"
                         :statsColumns="statsColumns"
                         :instruments="instruments"
            />
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
            "instruments"
        ],
        data() {
            let sortOrders = {}
            this.statsColumns.forEach(function(column) {
                sortOrders[column] = 1;
            })
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

                            x = a[sortKey].instrument
                            y = b[sortKey].instrument
                            return x.localeCompare(y) * order;
                        return order
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

<style scoped>
    body {
        font-family: Helvetica Neue, Arial, sans-serif;
        font-size: 14px;
        color: #444;
    }

    table {
        border: 2px solid #42b983;
        border-radius: 3px;
        background-color: #fff;
        margin-left:auto;
        margin-right:auto;
    }

    th {
        background-color: #42b983;
        color: rgba(255, 255, 255, 0.66);
        cursor: pointer;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    td {
        background-color: #f9f9f9;
    }

    th,
    td {
        min-width: 80px;
        padding: 10px 20px;
    }

    th.active {
        color: #fff;
    }

    th.active .arrow {
        opacity: 1;
    }

    .arrow {
        display: inline-block;
        vertical-align: middle;
        width: 0;
        height: 0;
        margin-left: 5px;
        opacity: 0.66;
    }

    .arrow.asc {
        border-left: 4px solid transparent;
        border-right: 4px solid transparent;
        border-bottom: 4px solid #fff;
    }

    .arrow.dsc {
        border-left: 4px solid transparent;
        border-right: 4px solid transparent;
        border-top: 4px solid #fff;
    }
</style>