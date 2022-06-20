<script setup lang="ts">

import _ from 'lodash';
import { Ref, ref } from 'vue';

defineProps({
  placeholder: String,
});

const emit = defineEmits(['change']);

const textValue: Ref<string> = ref('');

const debounceMilliseconds = 500;
const debouncedFunction = _.debounce(() => {
  emitValueChanged();
}, debounceMilliseconds);


function handleTextValueChanged() {
  debouncedFunction();
}

function clearTextValue() {
  textValue.value = '';
  emitValueChanged();
}

function emitValueChanged() {
  emit('change', textValue.value);
}
</script>

<template>
  <div>
    <Button type="button" icon="pi pi-filter-slash" label="Clear" class="p-button-outlined"
            @click="clearTextValue" />
    <span class="p-input-icon-right">
            <InputText :placeholder="placeholder"
                       v-model="textValue"
                       @input="handleTextValueChanged" />
            <i class="pi pi-search" />
          </span>
  </div>
</template>

