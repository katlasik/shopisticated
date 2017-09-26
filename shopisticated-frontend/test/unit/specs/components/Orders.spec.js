import { state, store } from 'store'
import Orders from '@/components/Orders'
import { beforeEachHooks, afterEachHooks, shallow, waitForUpdate } from 'vue-unit'

describe('Orders.vue', () => {
  beforeEach(beforeEachHooks)

  it('should call store action on creation', (done) => {
    const dispatch = sinon.spy(store, 'dispatch')

    shallow(Orders)

    waitForUpdate(() => { })
      .then(() => {
        expect(dispatch).to.be.calledWith('fetchOrders')
      }).end(done)

    dispatch.restore()
  })

  it('should render correct contents', () => {
    const order = {
      name: 'test',
      quantity: 5,
      date: new Date().toISOString()
    }

    state.orders = [order]

    const vm = shallow(Orders)

    expect(vm.$el.querySelectorAll('b-alert'))
      .to.have.lengthOf(1)

    expect(vm.$el.querySelector('h6').textContent)
      .to.have.string(order.name)
      .and.to.have.string(order.quantity)
  })

  it('should render correct contents on multiple orders', () => {
    state.orders = [{
      name: 'test',
      quantity: 5,
      date: new Date().toISOString()
    }, {
      name: 'test',
      quantity: 5,
      date: new Date().toISOString()
    }, {
      name: 'test',
      quantity: 5,
      date: new Date().toISOString()
    }]

    const vm = shallow(Orders)

    expect(vm.$el.querySelectorAll('b-alert'))
      .to.have.lengthOf(3)

    afterEach(afterEachHooks)
  })
})
