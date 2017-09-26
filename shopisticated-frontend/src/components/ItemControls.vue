<template>
  <b-button-toolbar>
    <b-input-group size="sm"  left="Quantity">
      <b-form-input v-model="quantity" type="number" min="0"></b-form-input>
    </b-input-group>
    <b-button-group size="sm">
      <b-btn @click="click" :disabled="disabled">Buy</b-btn>
    </b-button-group>
  </b-button-toolbar>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
      required: true
    }
  },
  computed: {
    disabled() {
      return this.quantity > this.item.available
    }
  },
  data() {
    return {
      quantity: 1
    }
  },
  methods: {
    click() {
      this.$emit('click', {
        quantity: this.quantity,
        item: this.item
      })
    }
  }
}
</script>

<style scoped>
</style>