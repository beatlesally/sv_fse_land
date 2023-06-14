<template>
    <base-layout page-title="The Details" page-default-back-link="/memories">
        <h2>The Details</h2>
    </base-layout>
</template>

<script>
import { defineComponent } from 'vue';
import { IonList, IonItem } from '@ionic/vue';

export default defineComponent({
  components: {
    IonList,
    IonItem
  },
});
</script>

