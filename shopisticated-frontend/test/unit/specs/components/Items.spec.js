import { state, store } from 'store'
import Items from '@/components/Items'
import { beforeEachHooks, afterEachHooks, shallow, waitForUpdate } from 'vue-unit'

describe('Items.vue', () => {
  let dispatch

  before(() => {
    dispatch = sinon.spy(store, 'dispatch')
  })

  beforeEach(beforeEachHooks)

  it('should render correct contents', () => {
    const item = {
      name: 'test',
      description: 'Lorem ipsum',
      image: 'static/images/a.jpg',
      available: 20
    }

    state.items = [item]

    const vm = shallow(Items)

    expect(vm.$el.querySelectorAll('.item'))
      .to.have.lengthOf(1)

    expect(vm.$el.querySelector('h2'))
      .to.have.text(item.name)

    expect(vm.$el.querySelector('p'))
      .to.have.text(item.description)

    expect(vm.$el.querySelector('img'))
      .to.have.attribute('src', item.image)
  })

  it('should call store action on creation', (done) => {
    shallow(Items)

    waitForUpdate(() => { })
      .then(() => {
        expect(dispatch).to.be.calledWith('fetchItems')
      }).end(done)
  })

  it('should call store action on purchase method', (done) => {
    const item = {
      name: 'test',
      description: 'Lorem ipsum',
      image: 'static/images/a.jpg',
      available: 20
    }

    state.items = [item]

    const vm = shallow(Items)

    waitForUpdate(() => {
      vm.purchase({
        item: item,
        quantity: 1
      })
    }).then(() => {
      expect(dispatch).to.be.calledWith('purchase')
    }).end(done)
  })

  it('should render correct contents for multiple elements', () => {
    const item = {
      name: 'test',
      description: 'Lorem ipsum',
      image: 'static/images/a.jpg',
      available: 20
    }

    state.items = [item]

    const vm = shallow(Items)

    expect(vm.$el.querySelectorAll('.item'))
      .to.have.lengthOf(1)

    expect(vm.$el.querySelector('h2'))
      .to.have.text(item.name)

    expect(vm.$el.querySelector('p'))
      .to.have.text(item.description)

    expect(vm.$el.querySelector('img'))
      .to.have.attribute('src', item.image)
  })

  after(() => {
    dispatch.restore()
  })

  afterEach(afterEachHooks)
})
