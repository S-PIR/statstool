<template>
    <transition name="modal-fade">
        <div class="modal-backdrop">
            <div class="modal"
                 role="dialog"
                 aria-labelledby="modalTitle"
                 aria-describedby="modalDescription"
            >
                <header class="modal-header" id="modalTitle">
                    <slot name="header">
                        <button
                                type="button"
                                class="btn-close"
                                @click="close"
                                aria-label="Close modal"
                        >
                            X
                        </button>
                    </slot>
                </header>
                <section class="modal-body" id="modalDescription">
                    <slot name="body">
                        <div style="padding: 10px 10px;">Fill the form to create new entry.</div><br>
                        Date:
                        <div>
                            <input type="date" v-model="statusDate" />
                        </div><br>
                        Instrument:
                        <div>
                            <input type="text" v-model="finInstrument" placeholder="Financial instrument" />
                        </div><br>
                        Price:
                        <div>
                            <input type="number" v-model="price" placeholder="Price" />
                        </div>
                        <div></div>
                    </slot>
                </section>
                <footer class="modal-footer">
                    <slot name="footer">
                        <button
                                type="button"
                                class="btn-green"
                                @click="save"
                                aria-label="Close modal"
                        >
                            Save
                        </button>
                    </slot>
                </footer>
            </div>
        </div>
    </transition>
</template>

<script>
    export default {
        props: ['stats'],
        data() {
            return {
                id: '',
                statusDate: '',
                finInstrument: '',
                price: '',
                showModal: false,
            }
        },
        watch: {
            statsAttr(newVal, oldVal) {
                this.id = newVal.id
                this.statusDate = newVal.statusDate
                this.finInstrument = newVal.finInstrument.instrumentName
                this.price = newVal.price
            }
        },
        methods: {
            close() {
                this.$emit('close');
            },
            save() {
                const dailyStats = { id: this.id, statusDate: this.statusDate, finInstrument: this.finInstrument, price: this.price,  }
                this.$resource('/stats{/id}').save({}, dailyStats).then(result =>
                    result.json().then(data => {
                        this.stats.push(data)
                        this.id = ''
                        this.statusDate = ''
                        this.finInstrument = ''
                        this.price = ''
                    })
                )
                this.$emit('close')
            }
        }
    }
</script>

<style>
</style>