<template>
    <transition name="modal-fade">
        <div class="modal-backdrop">
            <div class="modal"
                 role="dialog"
                 aria-labelledby="modalTitle"
                 aria-describedby="modalDescription"
            >
                <header
                        class="modal-header"
                        id="modalTitle"
                >
                    <slot name="header">
                        Fill the form to create new entry.

                        <button
                                type="button"
                                class="btn-close"
                                @click="close"
                                aria-label="Close modal"
                        >
                            x
                        </button>
                    </slot>
                </header>
                <section
                        class="modal-body"
                        id="modalDescription"
                >
                    <slot name="body">
                        <input type="text" v-model="statusDate" />
                        <input type="text" placeholder="Financial instrument" v-model="finInstrument" />
                        <input type="number" v-model="price" />
                    </slot>
                </section>
                <footer class="modal-footer">
                    <slot name="footer">
                        I'm the default footer!

                        <button
                                type="button"
                                class="btn-green"
                                @click="save"
                                aria-label="Close modal"
                        >
                            Close me!
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
            }
        }
    }
</script>

<style>
</style>