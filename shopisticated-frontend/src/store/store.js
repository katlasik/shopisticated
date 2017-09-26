import Vue from 'vue'
import Vuex from 'vuex'
import events from 'events'
import backend from 'backend'

Vue.use(Vuex)

export const state = {
  items: [],
  orders: []
}

export const mutations = {
  FETCH_ORDERS(state, orders) {
    state.orders = orders
  },
  FETCH_ITEMS(state, items) {
    state.items = items
  },
  PURCHASE(state, {item, quantity}) {}
}

export const actions = {
  fetchOrders ({ commit, state }) {
    backend.get('orders')
    .then((response) => {
      commit('FETCH_ORDERS', response.data)
    }).catch(() => {
      events.$emit('notify', 'Sorry, we have problems with our service. Maybe try in second?', 'error')
    })
  },
  fetchItems ({ commit, state }) {
    backend.get('items')
    .then((response) => {
      commit('FETCH_ITEMS', response.data)
    }).catch(() => {
      events.$emit('notify', 'Sorry, we have problems with our service. Maybe try in second?', 'error')
    })
  },
  purchase({commit}, {item, quantity}) {
    backend.post('orders', {
      itemId: item.id,
      quantity: quantity
    }).then(() => {
      item.available -= quantity
    })

    const msg = `You have bought ${quantity} of ${item.name}.`
    events.$emit('notify', msg)
    commit('PURCHASE', {item, quantity})
  }
}

export const store = new Vuex.Store({
  state,
  actions,
  mutations
})

export default store
