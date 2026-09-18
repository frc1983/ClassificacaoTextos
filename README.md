# Manejo Vertical

Site institucional estático da [Manejo Vertical](https://manejovertical.com.br/), recriado em **Astro** a partir do WordPress (tema `astra-child-manejo`). Sem PHP, banco ou painel: o conteúdo fica em arquivos de código para manutenção por IA.

## Páginas

| Rota | Arquivo | Conteúdo |
| --- | --- | --- |
| `/` | `src/pages/index.astro` | Hub: escolha entre os dois serviços |
| `/manutencao-predial/` | `src/pages/manutencao-predial.astro` | Landing de manutenção predial |
| `/poda-arvores/` | `src/pages/poda-arvores.astro` | Landing de poda de árvores |

A URL antiga `/manutencao-predial-em-altura-compromisso-com-a-qualidade/` redireciona para `/manutencao-predial/`.

## Onde editar textos e mídia

Tudo que uma IA (ou o dono do site) precisa mudar está em `src/data/`:

- `site.ts` — nome, WhatsApp, Instagram, SEO, tags do Google Ads
- `home.ts` — textos da home
- `predial.ts` — página de manutenção predial
- `poda.ts` — página de poda
- `types.ts` — formato dos dados

Imagens e vídeos ficam em `public/images/` e `public/videos/`. Estilos em `src/styles/global.css`. Interações (menu, carrossel, formulário) em `src/scripts/main.ts`.

## Formulário e WhatsApp

O formulário **não usa backend**. Ele monta a mensagem e abre `https://wa.me/5551997846870`. Cliques em links `wa.me` disparam a conversão do Google Ads (`AW-18337213268/y8GVCNbGl-EcENTW7qdE`).

## Desenvolvimento

```bash
npm install
npm run dev
```

Abre em `http://localhost:4321/`.

```bash
npm run build
```

Gera HTML estático em `dist/`.

## Publicar na Hostinger (cutover)

1. No WordPress/Hostinger: **backup completo** (arquivos + banco).
2. Neste projeto: `npm run build`.
3. No Gerenciador de Arquivos ou FTP da Hostinger, esvazie `public_html` (depois do backup) e envie **o conteúdo** de `dist/` (incluindo `.htaccess`).
4. Confira SSL do domínio `manejovertical.com.br`.
5. Teste:
   - `/`, `/manutencao-predial/`, `/poda-arvores/`
   - redirect da URL longa
   - botões e formulário do WhatsApp
   - layout no celular
6. Só então desative WordPress/plugins para não manter PHP sem necessidade.

O `.htaccess` em `public/` já cobre HTTPS, barra no final, 301 da URL antiga e página 404.

## Observação sobre o repositório

Este código foi gerado em uma branch do repositório que estava conectado ao agente. O ideal é versioná-lo em um repositório **novo e vazio** (por exemplo `manejovertical`) em vez de misturar com o projeto Java `ClassificacaoTextos`.
