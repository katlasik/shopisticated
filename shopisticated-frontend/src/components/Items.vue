<template>
  <b-container>
    <b-row class="item" v-for='(item, index) in items' :key='index'>
      <b-col md="6">
        <img slot='aside' sm="false" :src='item.image' :alt='item.name' />
      </b-col>
      <b-col md="6">
        <h2>{{item.name}}</h2>
        <p>{{item.description}}</p>
        <p><b>In Stock:</b> {{item.available}}</p>
        <item-controls @click="purchase" :item="item"></item-controls>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>

import ItemControls from '@/components/ItemControls.vue'
import store from 'store'

export default {
  components: {
    ItemControls
  },
  computed: {
    items() {
      return store.state.items
    }
  },
  created() {
    store.dispatch('fetchItems')
  },
  methods: {
    purchase(e) {
      store.dispatch('purchase', e)
    }
  }
}
</script>

<style scoped lang='scss'>
img {
  max-width: 20vw;
}

.item {

  flex-wrap: wrap;

  &  + .item {
    margin-top: 2rem;
  }
}
</style>
