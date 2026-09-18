import { defineConfig } from 'astro/config';
import sitemap from '@astrojs/sitemap';

export default defineConfig({
  site: 'https://manejovertical.com.br',
  trailingSlash: 'always',
  integrations: [sitemap()],
  redirects: {
    '/manutencao-predial-em-altura-compromisso-com-a-qualidade': '/manutencao-predial/',
  },
});
