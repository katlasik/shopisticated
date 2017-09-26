import Summary from '@/pages/Summary'
import { beforeEachHooks, afterEachHooks, shallow } from 'vue-unit'

describe('Summary.vue', () => {
  beforeEach(beforeEachHooks)

  it('should render correct contents', () => {
    const vm = shallow(Summary)

    expect(vm.$el).to.have.class('summary')
  })

  afterEach(afterEachHooks)
})
