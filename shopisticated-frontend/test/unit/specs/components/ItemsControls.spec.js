import ItemControls from '@/components/ItemControls'
import { beforeEachHooks, afterEachHooks, shallow, mount, waitForUpdate, simulate } from 'vue-unit'

describe('ItemControls.vue', () => {
  beforeEach(beforeEachHooks)

  it('should emit click event', (done) => {
    const item = {
      name: 'test',
      description: 'Lorem ipsum',
      image: 'static/images/a.jpg',
      available: 20
    }

    const vm = mount(ItemControls, {
      item
    })
    const spy = sinon.spy(vm, '$emit')

    waitForUpdate(() => {
      simulate(vm.$el.querySelector('b-btn'), 'click')
    })
      .then(() => {
        expect(spy)
          .to.be.calledWith('click', {
            quantity: 1,
            item: item
          })
      }).end(done)
  })

  it('should render correct contents', (done) => {
    const item = {
      name: 'test',
      description: 'Lorem ipsum',
      image: 'static/images/a.jpg',
      available: 20
    }

    const vm = shallow(ItemControls, {
      item
    })

    waitForUpdate(() => { })
      .then(() => {
        expect(vm.$el.querySelector('b-form-input'))
          .to.have.attribute('type', 'number')

        expect(vm.$el.querySelector('b-btn'))
          .to.not.have.attribute('disabled')
      }).end(done)
  })

  it('should disable button when quantity is over availble items', (done) => {
    const item = {
      name: 'test',
      description: 'Lorem ipsum',
      image: 'static/images/a.jpg',
      available: 20
    }

    const vm = shallow(ItemControls, {
      item
    })

    waitForUpdate(() => {
      vm.quantity = 21
    })
      .then(() => {
        expect(vm.$el.querySelector('b-btn'))
          .to.have.attribute('disabled', 'disabled')
      }).end(done)
  })

  afterEach(afterEachHooks)
})
