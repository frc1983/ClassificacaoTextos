# Orientações para IA — Manejo Vertical

Leia este arquivo **antes** de alterar o código. Ele existe para outra IA (ou a mesma) manter o site sem redesenhar, sem reintroduzir WordPress e sem quebrar conversão.

O `README.md` é para humanos (dev local e cutover na Hostinger). Este arquivo é o playbook de manutenção.

Sempre que entregar uma mudança neste projeto: **atualize este `AGENTS.md`** se a estrutura, as regras ou os pontos de edição mudarem. Não apague este arquivo.

## O que é este projeto

Site institucional estático da [Manejo Vertical](https://manejovertical.com.br/) (Porto Alegre / RS): manutenção predial em altura e poda de árvores. Recriado a partir do WordPress (tema `astra-child-manejo`) para **código puro**, de propósito para manutenção por IA.

Não é CMS, blog, loja nem área logada. Não há PHP, banco, painel nem backend de formulário.

Repositório alvo: `https://github.com/nathanabreu/manejovertical.git`  
Pasta local do dono: `C:\Programas\GIT\manejo_vertical`

## Regras (não negociar sem pedido explícito)

1. **Fidelidade visual e de copy.** Não redesenhe. Não “modernize” layout, tipografia, cores ou tom de voz. Reproduza o site vivo, salvo correção de bug pedida.
2. **Conteúdo em dados, não em markup.** Textos, depoimentos, SEO, WhatsApp, Ads e listas de serviços vão em `src/data/`. Componentes só renderizam.
3. **Sem WordPress / Elementor / PHP / MySQL.** Sem Hostinger passwords no repo. Sem copiar ZIPs, PDFs ou documentos da biblioteca WP.
4. **Sem backend.** Formulário monta mensagem e abre `wa.me`. Não adicione API, e-mail server, serverless nem CRM sem pedido.
5. **Sem blog, loja, login, cookies banner extra** salvo pedido.
6. **URLs com barra final.** `trailingSlash: 'always'` no Astro. Rotas: `/`, `/manutencao-predial/`, `/poda-arvores/`.
7. **Não quebre Google Ads.** IDs e conversão em `src/data/site.ts`, `BaseLayout.astro` e `src/scripts/main.ts` precisam continuar alinhados.
8. **Seção Problemas fica oculta.** Existe em dados/componente, mas `#mv-problems { display: none; }` no CSS replica o WP ao vivo. Não reexiba sem pedido.
9. **Português do Brasil** em copy, commits e respostas ao dono do site.
10. **Não faça merge** deste site no `master` Java de `frc1983/ClassificacaoTextos`. Esse repo é outro projeto.

## Stack

- Astro 5 (site estático), TypeScript
- `@astrojs/sitemap`
- CSS global único (`src/styles/global.css`) — sem Tailwind, sem CSS-in-JS
- JS de página em `src/scripts/main.ts` (navbar, menu mobile, carrossel, form WhatsApp, gtag)
- Build: `npm run build` → pasta `dist/` (gitignored) para upload no `public_html` da Hostinger
- Node: 22.x. `npm run dev` em `http://localhost:4321/`

## Mapa de arquivos

| Caminho | Função |
| --- | --- |
| `src/data/site.ts` | Nome, URL, WhatsApp, Instagram, Ads (`adsId`, `adsConversion`, `gtagId`), nav, SEO, `waLink()` |
| `src/data/home.ts` | Hero da home, cards de escolha, faixa de confiança |
| `src/data/predial.ts` | Landing manutenção predial (`predialPage`) |
| `src/data/poda.ts` | Landing poda (`podaPage`) |
| `src/data/types.ts` | Contratos (`ServicePage`, mídia, depoimento, etc.) |
| `src/pages/index.astro` | Home (hub) |
| `src/pages/manutencao-predial.astro` | Landing predial |
| `src/pages/poda-arvores.astro` | Landing poda |
| `src/pages/404.astro` | 404 |
| `src/layouts/BaseLayout.astro` | HTML, fontes, OG, gtag AW + GT |
| `src/components/ServiceLanding.astro` | Orquestra seções de uma landing |
| `src/components/*.astro` | Header, Footer, heros, cards, galeria, form, WhatsApp flutuante |
| `src/components/Icon.astro` | Ícones SVG por `name` |
| `src/styles/global.css` | Visual portado do tema WP + CSS customizado do site vivo |
| `src/scripts/main.ts` | Interações e conversão `wa.me` |
| `public/images/` | Fotos e favicons usados nas páginas |
| `public/videos/` | MP4s das galerias/serviços |
| `public/.htaccess` | HTTPS, trailing slash, 301 da URL longa WP, 404 |
| `public/robots.txt` | Robots |
| `astro.config.mjs` | `site`, sitemap, redirect da slug longa |

## Marca (não alterar sem pedido)

- Fontes: Inter (UI) + Playfair Display (títulos)
- `--olive: #15803D` (manutenção predial)
- `--purple: #6A3B7A` (poda)
- WhatsApp: `5551997846870` / `+55 51 99784-6870`
- Instagram: `@manejovertical`
- Ads: `AW-18337213268`
- Conversão: `AW-18337213268/y8GVCNbGl-EcENTW7qdE` (todo clique em `wa.me` + submit do form)
- Tag extra: `GT-TBBK62CR`

## Tarefas comuns

### Trocar texto, depoimento, telefone, SEO

Edite só `src/data/*.ts`. Rode `npm run dev` e confira a página.

### Trocar foto/vídeo

1. Coloque o arquivo em `public/images/` ou `public/videos/`.
2. Atualize o `src` correspondente em `src/data/`.
3. Use caminhos públicos (`/images/...`, `/videos/...`).
4. Não suba mídia que não aparece no site.

### Adicionar um serviço numa landing

Em `predial.ts` ou `poda.ts`, acrescente um item em `services` no formato `ServiceItem` (`types.ts`): `title`, `desc`, `media` (`image` ou `video` com `poster`).

### Nova página institucional (só se pedirem)

1. Dados em `src/data/<nome>.ts`.
2. Página em `src/pages/<slug>.astro` (pasta = URL com trailing slash).
3. Link em `homeNav` / `predialNav` / `podaNav` em `site.ts`.
4. SEO (`title`, `description`, `path`) em `site.ts`.
5. Reuse `BaseLayout` e, se for landing de serviço, `ServiceLanding` + tipo `ServicePage`.

### Ícones

`Icon.astro` aceita: `building`, `tree`, `shield`, `people`, `check`, `wind`, `drop`, `tool`, `shield-alt`, `arrow-down`, `plant`, `info`, `medal`, `bolt`, `home`, `circle`, `arrow`, `phone`, `pin`. Nome desconhecido cai em `shield`. Para ícone novo, acrescente o path SVG nesse arquivo.

### Redirects

Slug WP antiga: `/manutencao-predial-em-altura-compromisso-com-a-qualidade/` → `/manutencao-predial/`. Está em `astro.config.mjs` e em `public/.htaccess`. Mantenha os dois.

## O que o JS faz (`src/scripts/main.ts`)

- Navbar `scrolled` após 60px
- Menu mobile `#mv-menu-btn` / `#mv-mobile-menu`
- Smooth scroll em `a[href^="#"]`
- Carrossel do hero a cada 5s; clique no fundo avança slide
- Form `#mv-contact-form` valida e abre WhatsApp com nome/telefone/e-mail/mensagem
- `gtag('event','conversion',{ send_to: adsConversion, value: 1, currency: 'BRL' })` no submit e em qualquer clique `a[href*="wa.me"]`

Se mudar o número do WhatsApp, altere `site.ts` **e** a constante `WHATSAPP_NUMBER` em `main.ts` (hoje estão duplicadas). Preferível unificar se for mexer nisso.

## CSS: pegadinhas do site vivo

O final de `global.css` replica CSS extra que estava no WP:

- `#mv-problems { display: none; }` — seção problemas não aparece
- Galeria e depoimentos em carrossel horizontal (`display: flex` + scroll-snap)
- `body` em coluna com `order` das seções
- Home: hero partido predial/poda; no mobile o overlay some e o texto vai para fundo escuro

Não “limpe” esses hacks achando que são lixo.

## Publicar

1. Backup completo na Hostinger (arquivos + banco WP) **antes** do cutover.
2. `npm run build`
3. Enviar o **conteúdo** de `dist/` (incluindo `.htaccess`) para `public_html`.
4. Conferir SSL, as 3 rotas, 404, redirect da URL longa, WhatsApp, Ads, layout mobile.

Detalhe passo a passo: `README.md`.

## Verificação mínima depois de editar

- `npm run build` sem erro
- Home, predial e poda abrem
- CTA/form abrem `wa.me` com o número certo
- Trailing slash nas rotas internas
- Não reapareceu a seção Problemas (a menos que o pedido seja exatamente esse)

Se a mudança for visual, conferir desktop e mobile. Sem browser, use preview Astro (`npm run preview`) e diga o que não deu para clicar.

## Fora de escopo (recusar ou pedir confirmação)

- Restaurar WordPress / migrar de volta para PHP
- Copiar biblioteca de mídia WP inteira ou arquivos sensíveis
- Checkout, login, blog, e-commerce
- Trocar de framework (Next, etc.) sem pedido
- Enviar o site para o `master` de `ClassificacaoTextos`
