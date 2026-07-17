# JustARod Minecraft 1.20.1 Backport

This repository is a public, community-maintained backport of the original
[CSneko/JustARod](https://github.com/CSneko/JustARod) project.

## Project status

- Target: Minecraft 1.20.1 with Fabric
- Upstream baseline: version 0.2.2 at commit `f463a46698a37f95cfcd2e98dd70c38078b986c6`
- Required backport: toNeko for Minecraft 1.20.1
- Development branch: `1.20.1-backport`
- Status: the Minecraft 1.20.1/Java 17 source backport builds successfully.
  Dedicated-server loading reaches the EULA stop, and the development client
  loads resources, starts an integrated server, and joins a world.
- Relationship to upstream: unofficial; issues specific to this backport belong in this fork

## Development setup

Build the sibling `toNeko` repository's `1.20.1-backport` branch first. This
project compiles against
`../toNeko/fabric/build/libs/toneko-fabric-1.9.0+1.20.1-backport.1.jar` so it
cannot accidentally resolve an upstream toNeko artifact for Minecraft 1.21.1.

The remaining source migration is grouped around Minecraft 1.21 payload
networking, data components/enchantments, registry entries and attributes,
entity/rendering signatures, and tooltip/screen APIs.

Completed source groups:

- Fabric 1.20.1 play networking and property synchronization now use
  `Identifier` channels with `PacketByteBuf` codecs.
- Custom attributes and status-effect callbacks use the 1.20.1 registry and
  modifier APIs.
- JustARod custom item components now use stable `ItemStack` NBT keys, with
  shared Kotlin and Java accessors for save and packet compatibility.
- Core entities, tracked data, AI goals, GeckoLib animation controllers, and
  entity renderers now use their Minecraft 1.20.1/GeckoLib 4.4 interfaces.
- All Kotlin sources now compile against Minecraft 1.20.1. Food definitions,
  armor materials, tooltips, item state, and legacy item callbacks have been
  migrated.
- Java sources and Mixins now compile and apply on 1.20.1. Development server
  and client startup smoke tests pass through mod initialization.

Minecraft 1.20.1 has no vanilla generic jump-strength or scale entity
attributes. This branch supplies a tracked JustARod scale attribute for living
entities and applies it to collision dimensions and vanilla living-entity
rendering. Jump-nerf effects adjust upward velocity when the entity jumps.

## License and attribution

The original project and this backport are licensed under GPL-3.0. Copyright
and attribution notices from upstream are retained. When distributing modified
binaries, distribute the corresponding source and GPL-3.0 license as required.
