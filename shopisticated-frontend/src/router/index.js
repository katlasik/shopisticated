import Vue from 'vue'
import Router from 'vue-router'
import Shop from '@/pages/Shop'
import Summary from '@/pages/Summary'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Shop',
      component: Shop
    },
    {
      path: '/summary',
      name: 'Summary',
      component: Summary
    }
  ]
})
