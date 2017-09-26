import { actions } from 'store'
import events from 'events'

describe('store.js', () => {
  it('should emit event and call mutation', () => {
    const commit = sinon.mock()
    const $emit = sinon.spy(events, '$emit')

    const args = {
      item: {
        name: 'test',
        description: 'test',
        available: 7
      },
      quantity: 5
    }

    actions.purchase({ commit }, args)

    expect(commit).to.be.calledWith('PURCHASE', args)
    expect($emit).to.be.calledOnce
  })
})
