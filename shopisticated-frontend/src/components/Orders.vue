<template>
  <b-container>
    <b-alert show v-for='(order, index) in orders' :key='index'>
        <h5>{{ moment(order.date)  }}</h5>
        <h6>{{ message(order) }}</h6>
    </b-alert>
  </b-container>
</template>

<script>
import ItemControls from '@/components/ItemControls.vue'
import store from 'store'
import moment from 'moment'

export default {
  components: {
    ItemControls
  },
  computed: {
    orders() {
      return store.state.orders
    }
  },
  created() {
    store.dispatch('fetchOrders')
  },
  methods: {
    message({name, quantity}) {
      return `You have bougth ${quantity} of ${name}`
    },
    moment(date) {
      return moment(date).from()
    }
  }
}
</script>

<style scoped lang='scss'>

.media+.media {
  margin: 2rem 0;
}
</style>
