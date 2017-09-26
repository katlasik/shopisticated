<template>
  <div id="app">
    <topnav></topnav>
    <div class="content">
      <router-view></router-view>
    </div>
    <alerts></alerts>
  </div>
</template>

<script>
import Topnav from '@/components/Topnav'
import Alerts from '@/components/Alerts'

export default {
  components: {
    Topnav,
    Alerts
  },
  name: 'app'
}
</script>

<style scoped>
.content {
  padding: 1rem;
}
</style>
