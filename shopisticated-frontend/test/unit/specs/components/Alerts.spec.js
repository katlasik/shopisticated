import Alerts from '@/components/Alerts'
import events from 'events'
import { beforeEachHooks, afterEachHooks, mount, waitForUpdate } from 'vue-unit'

describe('Alerts.vue', () => {
  beforeEach(beforeEachHooks)

  it('should show toast on event', (done) => {
    const vm = mount(Alerts)

    const spy = sinon.spy(vm.$refs.toast, 'showToast')
    waitForUpdate(() => {
      events.$emit('notify', 'test message')
    }).then(() => {
      expect(spy).to.be.calledWith('test message')
    }).end(done)
  })

  afterEach(afterEachHooks)
})
