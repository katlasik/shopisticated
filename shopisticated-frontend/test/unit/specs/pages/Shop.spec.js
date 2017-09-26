import Shop from '@/pages/Shop'
import { beforeEachHooks, afterEachHooks, shallow } from 'vue-unit'

describe('Shop.vue', () => {
  beforeEach(beforeEachHooks)

  it('should render correct contents', () => {
    const vm = shallow(Shop)

    expect(vm.$el).to.have.class('shop')
  })

  afterEach(afterEachHooks)
})
