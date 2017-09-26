<template>
    <div class="alerts">
        <vue-toast ref='toast'></vue-toast>
    </div>
</template>

<script>
import events from 'events'
import 'vue-toast/dist/vue-toast.min.css'

import VueToast from 'vue-toast'

export default {
  components: {
    VueToast
  },
  created() {
    events.$on('notify', this.show)
  },
  methods: {
    show(msg, kind = 'info') {
      this.$refs.toast.showToast(msg, {
        theme: kind
      })
    }
  }
}
</script>

<style lang="scss">
    .alerts a {
        color: inherit;
        text-decoration: underline;

        &:hover {
            font-weight: bold;
        }
    }
</style>
