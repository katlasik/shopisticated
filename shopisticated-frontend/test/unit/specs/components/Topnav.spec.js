import Topnav from '@/components/Topnav'
import { beforeEachHooks, afterEachHooks, shallow } from 'vue-unit'
import Router from 'vue-router'

describe('Topnav.vue', () => {
  beforeEach(beforeEachHooks)

  it('should render correct contents', () => {
    const vm = shallow(Topnav, {
      router: Router
    })
    expect(vm.$el.querySelectorAll('.nav-item'))
       .to.have.lengthOf(2)
  })

  afterEach(afterEachHooks)
})
