# JustARod Minecraft 1.20.1 Backport

This repository is a public, community-maintained backport of the original
[CSneko/JustARod](https://github.com/CSneko/JustARod) project.

## Project status

- Target: Minecraft 1.20.1 with Fabric
- Upstream baseline: version 0.2.2 at commit `f463a46698a37f95cfcd2e98dd70c38078b986c6`
- Required backport: toNeko for Minecraft 1.20.1
- Development branch: `1.20.1-backport`
- Status: the Minecraft 1.20.1/Java 17 toolchain now resolves all dependencies
  and reaches Kotlin source compilation. The 1.21 API migration is in progress;
  there is no usable release yet.
- Relationship to upstream: unofficial; issues specific to this backport belong in this fork

## Development setup

Build the sibling `toNeko` repository's `1.20.1-backport` branch first. This
project compiles against
`../toNeko/fabric/build/libs/toneko-fabric-1.9.0+1.20.1-backport.1.jar` so it
cannot accidentally resolve an upstream toNeko artifact for Minecraft 1.21.1.

The remaining source migration is grouped around Minecraft 1.21 payload
networking, data components/enchantments, registry entries and attributes,
entity/rendering signatures, and tooltip/screen APIs.

## License and attribution

The original project and this backport are licensed under GPL-3.0. Copyright
and attribution notices from upstream are retained. When distributing modified
binaries, distribute the corresponding source and GPL-3.0 license as required.
