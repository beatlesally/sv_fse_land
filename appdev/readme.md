# App Development with Ionic and Vue.js

## First Steps
Node.js wird benötigt.
```powershell
#Version von Node.js prüfen
node -v 
#Ionic CLI installieren, die für das Arbeiten mit Ionic benötigt wird
npm install -g @ionic/cli 

#Wizard zum Projekt erstellen starten
ionic start

#Typescript aus Projekt entfernen
npm uninstall --save typescript @types/jest @typescript-eslint/eslint-plugin @typescript-eslint/parser @vue/cli-plugin-typescript @vue/eslint-config-typescript
```
Mit dieser Seite wird das Projekt ent-typoscript-ifiziert und rein auf JavaScript umgestellt. (https://ionicframework.com/docs/vue/quickstart?_gl=1*14dvwwt*_ga*MTI0NDc1NTc3LjE2ODY2NjQ2Nzk.*_ga_REH9TJF6KF*MTY4NjcyOTM3NC40LjEuMTY4NjczMDI0OC4wLjAuMA..#build-your-way-with-typescript-or-javascript)

```powershell
#App wird ausgeführt in Browsertab -> localhost:8100/home
ionic serve
```